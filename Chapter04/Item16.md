## Item 16 : 계승하는 대신 구성하라

> 계승 : 어떤 클래스가 다른 클래스를 extends 함


<br/>
<br/>

### 요약

**상속(계승)은 강력한 도구지만 캡슐화 원칙을 침해하므로 문제를 발생시킬 소지가 있다.**

**상위 클래스와 하위클래스 사이에 IS-A 관계가 있을 때만 사용하는 것이 좋다.**

**IS-A 관계라도 서로 다른 패키지에 있거나 상속을 고려해 만들어진 상위클래스가 아니라면 하위 클래스는 깨지기 쉽다.**

**이런문제 때문에 구성과 전달 기법을 사용하는 것이 좋다.**

<br/>
<br/>
<br/>

### 메서드 호출과 달리, 계승은 캡슐화 원칙을 위반한다.

하위 클래스가 정상 동작하기 위해서는 상위 클래스의 구현에 의존할 수 밖에 없다.

상위 클래스 작성자가 계승을 고려해 클래스를 설계하고 문서까지 만들어 놓지 않았다면,

하위 클래스는 상위 클래스의 변화에 발맞춰 진화해야 한다.


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

-> final로 바꿔버리면 자바 버전 업그레이드 할때 각종 버그생김
-> 기업에서 버전업 하려고 안함
-> 자바 새버전 나온 의미가 없어져버림
-> 차라리 final로 바꾸지 말자

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
