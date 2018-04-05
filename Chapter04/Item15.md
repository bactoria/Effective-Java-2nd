## Item 15 : 변경 가능성을 최소화하라

<br/>
<br/>

### 요약

**1. 변경 가능한 클래스로 만들 타당한 이유가 없다면, 반드시 변경 불가능 클래스로 만들어야 한다.**
**2. 변경 불가능한 클래스로 만들 수 없다면, 변경 가능성을 최대한 제한하라.**
**3. 특별한 이유가 없다면 모든 필드는 final로 선언하라.**

<br/>
<br/>
<br/>

### 변경 불가능(Immutable) 클래스


어떤 메소드도 외부에서 관측 가능한 상태변화를 야기하지 않아야 한다.

**String class**

```Java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
```

<BR/>

**변경 불가능 클래스 규칙**

1. 객체 상태를 변경하는 메서드(수정자 메서드 등) 를 제공하지 않는다.
2. 계승할 수 없도록 한다. (보통 final class 선언)
3. 모든 필드를 final로 선언한다.
4. 모든 필드를 private로 선언한다.
5. 클라이언트는 변경가능 객체에 대한 참조를 획득할 수 없어야 한다.
(만약 객체나 배열을 getter로 반환했을 때 참조르 반환 해버리잖아.
그러니까 방어적 복사본(defensive copy)를 만들자. (규칙 39) )



생성자 대신 static factory method를 사용하여 인스턴스 생성을 한다면

생성자를 private하기 때문에

계승이 불가능하게 만들 수 있다.

<BR/>

**변경불가능 클래스 장점**

 * 설계하기 쉽다
 * 구현하기 쉽다
 * 사용하기 쉽다
 * 오류가능성 적다
 * 더 안전하다

<BR/>


 **변경불가능 클래스 단점**

* 값마다 별도의 객체를 만들어야 한다.

BigInteger의 경우 값 하나하나마다 객체다.

String의 경우도, String 변수의 값을 변경하려면 아애 새 객체를 생성해야 한다.

<BR/>

**BigInteger.class 이슈**

BigInteger.class와 BigDecimal.class는

final class 가 아니다.

따라서 두 클래스는 모두 재정의(Override) 가능하다.

하위 호환성 때문에 다음버전 자바에 final로 바뀔리도 없다.


-> 다음 JDK버전에 final로 바꾸면?
-> 기업에서 다음 JDK버전으로 넘어갈 때 각종 버그생김
-> 기업에서 버전업 하려고 안함
-> 자바 새버전 나온 의미가 없어져버림
-> 그냥 final로 바꾸지 말자

<BR/>

```Java
public static BigInteger safeInstance (BigInteger val) {
  if (val.getClass() != BigInteger.class)
    return new BigInteger(val.toByteArray());
  return val;
}
```

BigInteger를 반환한다면 방어적복사 시행하자.

val이 계승한 객체일 수도 있으니까..

## TODO : 직렬화 부분
이해안되서 패스함
p107~108
