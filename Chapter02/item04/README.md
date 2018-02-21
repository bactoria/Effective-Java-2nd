## Item 04: Enforce noninstantiability with a private constructor
(객체 생성을 막을 때는 private 생성자를 사용하라 )

**객체 생성을 막는 것에는 어떤것들이 있을까?**
1. `java.lang.Math`
2. `java.util.Arrays`
3. `java.util.Collections`

(위 클래스들은 객체를 만들 목적의 클래스가 아니다.)

---
## 1. `java.lang.Math`
```java
public final class Math {

    /**
     * Don't let anyone instantiate this class.
     */
    private Math() {}

    public static final double PI = 3.14159265358979323846;

    public static int max(int a, int b) {
      return (a >= b) ? a : b;
    }

    ...//생략!
    }
```

Math.max(), Math.sqrt() 뭐 이런것들을 객체생성해서 썼던가? 아니다.

`java.lang.Math`클래스는 변수, 메소드 모두 `static`으로 선언되어 있기 때문에 클래스가 로딩될 때 static자원들은 메모리에 올라가서 프로그램 종료시 소멸된다.

---
## 2. `java.util.Arrays`
```java
public class Arrays {
  // Suppresses default constructor, ensuring non-instantiability.
  private Arrays() {}

  public static void sort(int[] a) {
    DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
  }
  ...//생략
}
```
Arrays.sort() 도 객체생성없이 사용할 수 있었는데,

여기도 죄다 `static`으로 선언되어 있다.

---
## 3. `java.util.Collections`
```java
public class Collections {
  // Suppresses default constructor, ensuring non-instantiability.
  private Collections() { }

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<? super T>> void sort(List<T> list) {
      list.sort(null);
  }
  ...//생략

}
```
Collections.sort() 이것도 마찬가지다. `static` 덕분에 객체생성 없이 사용할 수 있다.


---
```java
//객체를 만들 수 없는 유틸리티 클래스
public class UtilityClass {

  //기본 생성자가 자동 생성되지 못하도록 하여 객체 생성 방지
  private UtilityClass(){
    throw new AssertionError();
  }
...//나머지는 생략
}
```

( 생성자를 명시적으로 정의했으나 직관적이지 않으니, 위처럼 주석을 달아주는 것이 바람직하다.)

이렇게하면 하위클래스도 만들 수 없다.

하위클래스에서는 명시적이든 묵시적이든 호출할 수 있어야하는데 호출할 수가 없기 때문이다.

> *`java.lang.Math` 는 final 선언되어있다.*  
> *다른 두개는 왜 final 선언 안한걸까?*
