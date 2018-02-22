## Item 05 : 불필요한 객체는 만들지 말라
(Avoid creating unnecessary objects)

**불필요한 객체 만들지 않는법**
1. String
2. static factory method
3. static initializer
4. Wrapper class

---
## 1. String
```java
  String s1 = new String("ABC");
  String s2 = new String("ABC");

  System.out.println(s2 == s1); //false(참조값 다름)
```
s1, s2에서 각각 객체생성을 했으므로 힙영역에 객체 2개가 생성이 된다.

`String.class`는 불변 클래스(immutable class)라서

같은 문자열을 다른 객체로 만들 이유가 없다.

객체 하나를 생성하고 같은 참조값을 가지면 된다.
(어짜피 문자열이 바뀌면 새로 객체를 생성하니까)
```java
  String s1 = "ABC";
  String s2 = "ABC";
  System.out.println(s2 == s1); //true(참조값 같음)

```
`s1`에서 `String Constant pool`에 문자열 "ABC"가 할당되고,

`s2`는 `s1`과 같은 참조값을 가진다.


## 2. static factory method
```java
  System.out.println(new Boolean("true")); //새로운 객체 생성
  System.out.println(Boolean.valueOf("true")); // static 자원 사용
```
Item01 에서의 static factory method의 2번째 장점과 같다.

## 3. static initializer
```java
Public class Person {

  private final Date birthDate;

  // 이렇게 하면 안 된다!
  public boolean isBabyBoomer() {

    // 생성 비용이 높은 객체를 쓸데없이 생성한다.
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone(“GMT”));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    Date BoomStart = gmsCal.getTime();
    gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
    Date boomEnd = gmtCal.getTime();

    return birthDate.compareTo(boomStart) >= 0 &&
    birthDate.compareTo(boomEnd) < 0;

    }
}
```

위에서 `isBabyBoomer()` 함수 호출시 마다
`Calendar`형 객체, `TimeZone`형 객체, `Date`형 객체 2개 를 만들어낸다.

모두 같은 값을 가지는데 함수 호출시마다 객체를 만들 이유가 없다.

```java
Public class Person {
  private final Date birthDate;
  // 다른 필드와 메서드, 생성자는 생략

	/** 베이비 붐 시대의 시작과 끝 **/
  private static final Date BOOM_START;
  private static final Date BOOM_END;

  static {

    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone(“GMT”));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_START = gmtCal.getTime();
    gmtCal_set(1965, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_END = gmtCal.getTime();

  }

  public boolean isBabyBoomer() {

    return birthDate.compareTo(BOOM_START) >= 0 &&
           birthDate.compareTo(BOOM_END) < 0;

  }

}
```
`static`을 이용하여 클래스 초기화시 객체를 한번씩만 만들게 되었다.

`BOOM_START`, `BOOM_END` 가 상수라는 것도 직관적으로 알 수 있다.

`isBabyBommer()`가 자주 실행될 수록 성능은 더 좋아질 것이다.

반면, 한번도 실행되지 않는다면 쓰데없는 초기화였다.


## 4. 기본 자료형
```java
Public static void main(String[] args) {

  Long sum = 0L;

  for ( long i = 0; i < Integer.MAX_VALUE; I++) {
    sum = sum + i ;
  }

  System.out.println(sum);

}
```
변수`sum`이 `Long`으로 선언되었다.

for문을 돌 때마다 새로운 `Long` 형 객체를 생성할 것이다.



```java
Public static void main(String[] args) {
  //위 코드에서
  //Long -> long 만 바꿈.
  long sum = 0L;

  for ( long i = 0; i < Integer.MAX_VALUE; I++) {
    sum = sum + i ;
  }

  System.out.println(sum);

}
```

`Long`에서 -> `long`으로만 바꿨다.

객체표현형 대신 기본자료형을 사용하고,
생각지도 못한 자동 객체화(Auto Boxing)가 발생하지 않도록 유의하자.
