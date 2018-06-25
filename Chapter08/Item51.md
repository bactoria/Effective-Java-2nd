# Item 51 : 문자열 연결 시 성능에 주의하라

&nbsp;
&nbsp;

Sting 은 불변객체이기 때문에 String끼리 더하면 

새로운 String 객체를 생성한다.

그래서 `for문` 안에 들어있다면

for문 수행시마다 새로운 `String 객체` 를 생성한다.

이를 해결하기 위해 `StringBuilder`를 사용할 수 있다.

&nbsp;

### 요약

&nbsp;
&nbsp;

### `String` vs `StringBuilder`

**String + String**

```Java

String result = "";

for(String s : Strings) {
    result += " ";
    result += s;
}

System.out.println(result);

```

`for문` 수행 시 마다 새로운 String 객체가 생성될 것이다.

( 참조변수 `result` 는 매번 새로 생성되는 String 객체를 참조 할 것이다. )

&nbsp;

**StringBuilder**

```java

StringBuilder result = new StringBuilder();

for(String s : Strings) {
    result.append(" ").append(s);
}

System.out.println(result);

```

&nbsp;
&nbsp;

### 추가로..

```java
String name = "Hwang" + " " + "Junoh";
```

위 코드는 컴파일러가 자동으로 아래와 같이 변경해주는 걸로 알고 있음.

```java
String name = new StringBuilder("Hwang").append(" ").append("Junoh");
```

반복문에서는 커버 못침. 최소 한번의 String 객체는 만들질테니까..