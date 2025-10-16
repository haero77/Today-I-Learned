<!-- TOC -->
* [17. CSS 적용방법(inline, internal, linked)과 선택자들](#17-css-적용방법inline-internal-linked과-선택자들)
  * [CSS 적용 방법](#css-적용-방법)
  * [CSS 선택자](#css-선택자)
  * [결합자와 가상클래스](#결합자와-가상클래스)
* [18. 글자와 텍스트 스타일](#18-글자와-텍스트-스타일)
* [19. 문단과 목록 스타일](#19-문단과-목록-스타일)
* [20. 색 표현하기](#20-색-표현하기)
* [21. 인라인 요소와 블록 요소](#21-인라인-요소와-블록-요소)
* [22. 박스 모델 1](#22-박스-모델-1)
* [23. 박스 모델 2](#23-박스-모델-2)
* [24. 배경 꾸미기](#24-배경-꾸미기)
* [25. 포지셔닝](#25-포지셔닝)
* [26. 요소들을 감추는 방법들](#26-요소들을-감추는-방법들)
* [27. Flex 레이아웃](#27-flex-레이아웃)
<!-- TOC -->

# 17. CSS 적용방법(inline, internal, linked)과 선택자들

> https://www.yalco.kr/@html-css/2-1/
> CSS 선택자 연습 사이트: https://flukeout.github.io/

## CSS 적용 방법

```html
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HTML & CSS 01-08-02</title>
  <!-- 외부 스타일 시트  -->
  <link rel="stylesheet" href="../style.css">
</head>
```

- 인라인은 비권장
- 내부 스타일 시트: 전체 코드가 많지 않을 때. 재사용 불가. 길어지면 가독성 저하.
- **외부 스타일 시트:** 재사용 가능. 유지보수 용이. 협업에 유리.

## CSS 선택자

```css
/* 모든 요소 선택 */
* {
  font-weight: bold;
  color: darkorange;
}

/* 같은 선택자의 경우 뒤에 오는 것이 우선순위 높음 */
* {
  color: plum;
}

/* 태그 선택자. 모든 요소 선택자보다 우선순위 가짐. */
p {
  color: olivedrab;
}

/* class 선택자 */
/* 태그보다 우선순위 높음 */
/* 페이지상의 여러 요소가 같은 class를 가질 수 있음 */
.blue {
  color: lightblue;
}

/* 다른 선택자에 이어붙일 수 있음(태그, 클래스 등...) */
/* 선택자는 구체적일수록 우선순위 높음 */
p.blue {
  color: slateblue;
}

.blue.dark {
  color: mediumblue;
}

/*p.blue.dark {*/
/*  color: navy;*/
/*}*/

/* id 선택자 */
/* class보다 우선순위 높음 */
/* id는 페이지상에서 요소마다 고유해야 함 */
#red {
  color: tomato;
}

/* 그룹 선택자 */
span, .dark, #red {
  text-decoration: underline;
}
```

## 결합자와 가상클래스

```css
/* 자손 결합자 */
.outer li {
  color: olivedrab;
}

/* 자식(1촌 자손) 결합자 */
.outer > li {
  color: dodgerblue;
}

.outer > li > li {
  text-decoration: underline;
}

/* 뒤따르는 모든 동생들 결합자 */
.starter ~ li {
  font-style: italic;
}

/* 첫 번째, 마지막 요소 가상 클래스 */
ol li:first-child,
ol li:last-child {
  color: yellowgreen;
}

/* ~가 아닌 요소 가상 클래스 */
.outer li:not(:last-child) {
  text-decoration: line-through;
}

/* `outer` 클래스가 아닌 `ul` 내부의 `li`에 적용 */
ul:not(.outer) li {
  font-weight: bold;
}

/* n번째 자식 요소 가상 클래스 */
/* #, #n, #n+#, odd, even 등 시도해보기 */
ol li:nth-child(4n+1) {
  font-weight: bold;
  color: deeppink;
}

li:hover,
ol li:nth-child(4n+1):hover {
  font-weight: bold;
  color: blue;
}
```

# 18. 글자와 텍스트 스타일

# 19. 문단과 목록 스타일

# 20. 색 표현하기

# 21. 인라인 요소와 블록 요소

# 22. 박스 모델 1

# 23. 박스 모델 2

# 24. 배경 꾸미기

# 25. 포지셔닝

# 26. 요소들을 감추는 방법들

# 27. Flex 레이아웃