- 목표  
  - Manifest 파일에 대한 이해
  - Manifest.MF 에 원하는 header 와 attribute 를 추가하는 법
  - Manifest.MF에서 원하는 값을 추출하는 법
- 생각해볼 것
  - 스프링 컨테이너 내부에서, JAR 파일 밖의 정보를 읽을 수 있을까?
    - 그렇게 하는 것이 좋을까?

## Manifest 파일이란?

- JAR 패키지의 META-INF 패키지 안에 있는 파일이다.

```
- JAR 구조
  - BOOT-INF 
  - META-INF
    - MANIFEST.MF
  - org
```

- 말 그대로 프로젝트의 기본적인 메타정보들을 갖고 있다.
- key-value 구조로 되어있으며, 
  - key 는 header, value는 attribute 라고한다.
  
![img.png](Posting/Spring/img/img.png)
  
## Manifest 파일에 내가 원하는 정보를 추가하기

- build.gradle 에서, bootJar 태스크에 의해 JAR 파일이 생성된다.
- 따라서, bootJar 태스크에서 mainfest 정보를 추가할 수 있다.

### 빌드 시간을 확인해볼까?

```java
bootJar {
  manifest {
    attributes(
            'Build-Timestamp' : java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Seoul"))
                    .toLocalDateTime()
                    .truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
    )
  }
}
```

- bootJar 태스크에 key 는 `Build-Timestamp` 를, value 로는 현재 서울 시간을 갖게끔 작성한다.

![img_1.png](Posting/Spring/img/img_1.png)

- 다시 MANIFEST.MF 를 확인해보면, Build-Timestamp 가 추가된 걸 확인할 수 있다.


## Mainfest 파일에서 원하는 정보를 추출하기 

- 필요한 정보는 MANIFEST.MF 파일에 추가해두었다. 그럼 이제 이 값을 어떻게 추출해서 애플리케이션 내에서 사용할까?
- MANIFEST.MF 파일을 읽기 위해 필요한 파라미터는 JAR 파일의 경로와 Manifest 의 헤더값이다.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarInputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManifestReader {

    /**
     * META-INF/MANIFEST.MF 에 정의된 Attribute value를 읽는다.
     *
     * @param jarFilePath  JAR file Path build by build tools like 'Gradle'
     * @param attributeKey Attribute key of MANIFEST.MF
     * @return Attribute value in MANIFEST.MF matches attributeKey
     */
    public static String getManifestAttributeValue(String jarFilePath, String attributeKey) {
        try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFilePath))) {
            return jarInputStream
                    .getManifest()
                    .getMainAttributes()
                    .getValue(attributeKey);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Cannot Read JAR from %s", jarFilePath));
        }
    }

}
```

- try-with-resources 를 이용하여 MANIFEST.MF 를 읽는다. 
- 파일을 스트림으로 열고, 
  - Manifest 파일울 읽어들여(`getMainfest()`)
  - 속성들 중에서 (`getMainAttributes()`) 
  - attributeKey 를 헤더값으로 갖는 Attribute 의 값을 찾는다.(`getValue()`)

### 활용하기

```java
@RestController
public class VersionApi {

    private static final String MANIFEST_BUILD_DATE_KEY = "Build-Timestamp";
    private static final String JAR_FILE_PATH = "build/libs/labs-0.0.1-SNAPSHOT.jar";

    @GetMapping("/build-date")
    public String getBuildDate() {
        return ManifestReader.getManifestAttributeValue(JAR_FILE_PATH, MANIFEST_BUILD_DATE_KEY);
    }

}
```

- 별도의 설정 없이는 `build/libs/${애플리케이션}.jar` 위치는 변하지 않는다.


### 결과

![img_2.png](Posting/Spring/img/img_2.png)

### Reference

- https://www.baeldung.com/java-jar-manifest