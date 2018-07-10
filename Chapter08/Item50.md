# Item 50 : 다른 자료형이 적절하다면 문자열 사용은 피하라

&nbsp;
&nbsp;


### 요약

더 좋은 자료형이 있거나 만들 수 있을 때는 객체를 문자열로 표혆는 것은 피하라.

제대로 쓰지 못할 경우 문자열은 다른 자료형에 비해 다루기 성가시고, 유연성도 떨어지며, 느리고, 오류 발생 가능성도 높다.

문자열이 적합하지 못한 자료형으로는 `기본 자료형`, `enum`, `혼합 자료형` 등이 있다.

&nbsp;

### 문자열은 `값 자료형` 을 대신하기에는 부족하다

데이터를 읽어들일 때, 보통 문자열 형태로 들어온다.

이를 형변환 시켜주어야 사이드 이펙트를 줄일 수 있다.

&nbsp;

### 문자열은 `enum` 자료형을 대신하기에는 부족하다

enum은 문자열보다 훨씬 좋은 열거 자료형 상수들을 만들어낸다. (규칙 30 참조)

&nbsp;

### 문자열은 `혼합 자료형` 을 대신하기엔 부족하다

```java
String compoundKey = className + "#" + i.next();
```

`#` 를 이용하여 구분하는 코드인데, 이러면 문자열을 파싱하는 작업도 있어야 한다.

느리고, '#' 가 className에 들어 가 있다면 오류가 발생할 것이다.

혼합 자로형을 표현할 클래스를 새로 만들자. 보통 `private static` 멤버 클래스로 선언된다. (규칙 22)

### 문자열은 권한을 표현하기에 부족하다

```java
public class ThreadLocal {
  private ThreadLocal() { } // 객체를 만들 수 없다

  //주어진 이름이 가리키는 스레드 지역 변수의 값 설정
  public static void set(String key, Object value);

  //주어진 이름이 가리키는 스레드 지역 변수의 값 반환
  public static Object get(String key);
}
```

위 코드의 문제점.

`Key` 이름이 중복이 된다면 유일성이 보장되지 못함.

문자열 대신 `위조 불가능 키` 로 바꾸면 해결된다.

```java
public class ThreadLocal {
  private ThreadLocal() { } //객체를 만들 수 없다

  public static class Key { //(권한)
    Key() { }
  }

  //유일성이 보장되는, 위조 불가능 키를 생성
  public static Key getKey() {
    return new Key();
  }

  public static void set(Key key, Object value);
  public static Object get(Key key);
}
```

위 코드의 문제점

(무슨 문제인지 잘 모르겠다.)

```java
public final class ThreadLocal {
  public ThreadLocal();
  public void set(Object value);
  public Object get();
}
```

위 코드의 문제점

`Object`에서 `실제 자료형` 으로 형변환을 해야 하므로 형 안전성을 보장하지 못한다.

제너릭으로 해결가능하다.

**java.lang.ThreadLocal**
```
public final class ThreadLocal<T> {
  public ThreadLocal();
  public void set(T value);
}
```

&nbsp;
