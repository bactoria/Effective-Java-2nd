## Item 45 : 지역 변수의 유효범위를 최소화하라

&nbsp;
&nbsp;

### 요약

```java
for (int i = 0**, n = expensiveComputation()** ; i < n; i++) {
    doSomething(i);
}
```

&nbsp;
&nbsp;

### 지역변수의 유효범위를 최소화하는 가장 강력한 기법

**처음으로 사용하는 곳에서 선언**

사용하기 전에 미리 선언한다면 소스코드를 읽는 사람만 혼란스럽게 할 뿐이다.

실제로 변수가 사용될 때 쯤이면 자료형과 초기값은 잊어버릴 수 있다.

&nbsp;
&nbsp;

### while문 대신 for문

**for-each 문**

```java
for (Element e : c) {
    doSomething(e);
}

// Java 1.5 이전

for (Iterator i = c.iterator(); i.hasNext(); ){
    doSomething((Element)i.next());
}
```

**while 문**

```java
Iterator<Element> i = c.iterator();

while (i.hasNext()) {
    doSomething(i.next());
}

```

`for 문 / for-each 문` 에서의 순환문 변수는 () 와 {} 안으로 제한된다.

( + 코드가 간결해진다. )

따라서 `while 문` 보다 `for 문` 을 쓰는 것이 좋다.

&nbsp;
&nbsp;

### 지역 변수의 유효범위를 최소화하는 숙어

```java
for (int i = 0; i < expensiveComputation(); i++) {
    doSomething(i);
}
```

위 코드에서는 for문이 한번 실행 될 때 마다 

`expensiveComputation()` 함수가 한번씩 실행이 된다.

이를 개선시킨 코드는 아래와 같다.

( `expensiveComputation()` 은 `for문` 수행시마다 실행시키는 함수가 아니라고 가정하겠습니다. )

&nbsp;

```java

int n = expensiveComputation();

for (int i = 0; i < n; i++) {
    doSomething(i);
}
```

허나, 여기서 변수 `n` 은 `for 문` 내에서 사용하기 위한 것인데, 밖으로 뺼 필요가 없다.

더 최적화시킬 수 있다.

&nbsp;

```java
for (int i = 0, n = expensiveComputation(); i < n; i++) {
    doSomething(i);
}
```

이런건 생각해보지도 못했다..
