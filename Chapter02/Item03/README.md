## Item 03 : 싱글톤을 쓸 때 property는 private 생성자나 enum타입으로 하라
(Enforce the singleton property with a private constructor or an enum type)

**싱글톤 만드는법**
1. using public final field
2. using static Factory
3. using Enum

(결론: 1번 쓰지마라)

---
## 1. Using public final field
```java
public class Elvis {
  public static final Elvis INSTANCE = new Elvis();
  private Elvis(){...}

  public void leaveTheBuilding(){...}
}
```

#### 문제점
1. reflection으로 `private`생성자를 호출할 수 있다.
2. 객체를 생성하라는 요청을 받으면 예외를 던지도록 해야한다.

***
## 2. Using static Factory
```java
public class Elvis{
  private static final Elvis INSTANCE = new Elvis();
  private Elvis() {...}
  public static Elvis getInstance() {return INSTANCE; }

  public void leaveTheBuilding() {...}
}
```
#### 장점
1. API를 변경하지 않고도 싱글톤 패턴을 포기할 수 있다.
2. 제네릭타입을 수용하기 쉽다.
3. (`private` 생성자 때문에 상속이 안된다.)
#### 문제점
1. 앞에서의 reflection 공격이 가능하다.

### 싱글톤 직렬화(Serialization)
앞의 방법들에서 Serializable 클래스로 만들려면 `implements Serializable` 추가하는것으로 부족하다.
싱글톤 특성을 유지하려면 모든 필드를 transient로 선언하고 readResolve 메서드를 추가해야 한다.
그렇지 않으면 역직렬화 될 때마다 새로운 객체가 생기게 된다. 이를 막기 위해 아래의 readResolve메서드를 추가한다.

```java
private Object readResolve(){
  //동일한 Elvis 객체가 반환되도록 하는 동시에, 가짜 Elvis 객체는
  //GC가 처리하도록 만든다.
  return INSTANCE
}
  ```

***
## 3. Using Enum
```java
public enum Elvis{
  INSTANCE;

  public void leaveTheBuilding() {...}
}
```

#### 장점
1. reflection 공격에 안전하다.
2. 직렬화가 자동으로 처리된다.


JDK1.5에 추가된 `enum`을 이용하여 싱글톤을 구현할 수 있다고는 하나..

실제 개발쪽에서 쓰이는지 모르겠다.

---
>*클래스를 싱글턴으로 만들면 클라이언트를 테스트하기가 어려워질 수가 있다.
>싱글턴이 어떤 인터페이스를 구현하는 것이 아니면 가짜 구현으로 대체할 수 없기 때문이다.*

singleton class는 테스트할 때 mock으로 만들 수가 없다.

그래서 Singleton을 만들 때는 Interface를 하나 만들어 둔다. ?

#### 참고자료
[유튜브](https://www.youtube.com/watch?v=dKYARM4FNng)
