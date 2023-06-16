### Segmentation

- **_Segmentation이란 process가 할당받은 메모리 공간을 논리적 의미 단위(=segment)로 나누어, 연속되지 않는 물리 메모리 공간에 할당될 수 있도록 하는 메모리 관리 기법이다._**
- 일반적으로 프로세스의 메모리 영역 중 Code, Data, Heap, Stack 등의 기능 단위로 segment를 정의하는 경우가 많다.
- segmentation 기법에서는 주소 바인딩을 위해 모든 프로세스가 각각의 주소 변환을 위한 segment table 을 갖는다.
- **일정한 크기의 단위**로 나누어 할당을 했던 **page**와 다르게, **segmentation**은 **의미 단위**로 물리 메모리에 할당을 하는 기법

![](https://velog.velcdn.com/images/balparang/post/02d1da03-4924-4806-b247-85380c20a201/image.png)

2번 테이블의 base에서(4300), 250만큼 떨어진 곳이 물리 주소(=4550)

### Memory Fragmentation

- Segmentation 기법에서 Segment 크기 만큼 메모리를 할당하므로 내부 단편화 문제는 발생하지 않는다.
- 하지만 서로 다른 크기의 segment들이 메모리에 적재되고 제거되는 일이 반복되면, `외부 단편화 문제`가 발생할 가능성이 있다. 

### Paging Vs. Segmentation

- Paging은 일정한 크기의 단위로 나누어 할당한다.
- Segmentatoin 은 Code, Data, Stack, Heap 등 의미단위로 물리 메모리에 할당을 하는 기법이다. 
- Paging은 내부 단편화 문제가 발생
- Segmentation은 외부 단편화 문제가 발생

## Paged Segmentation

- Segmentation 기법을 기본으로 적용하되, 이를 다시 동일 크기의 page로 나누어 물리 메모리에 할당하는 메모리 관리 기법이다.
- 즉, 프로그램을 의미단위의 segment로 나누고, 개별 segment 의 크기를 page의 배수가 되도록 설정하는 방법이다.
- 이를 통해 segmentation 기법에서 발생하는 외부 단편화 문제를 해결하고 동시에 segment 단위로 프로세스 간의 공유나 process 내의 접근 권한 보호가 이루어지도록 해서 paging 기법의 단점을 해결한다.

<img src="https://velog.velcdn.com/images/balparang/post/00dcde57-62b0-42fa-a838-90ef113e9b85/image.png" width="500">

내부 단편화 문제는 발생할 수 있겠으나, 외부 단편화 문제는 발생하지 않는다.