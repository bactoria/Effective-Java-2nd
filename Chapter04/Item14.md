## Item 14 : public 클래스 안에는 public필드를 두지 말고 접근자 메서드를 사용하라

<br/>
<br/>
### 요약
**1. public class는 변경가능 필드를 외부로 공개하면 안된다.**
**2. package-private class/ private nested class의 필드는 public이 바람직할 때도 있다.**

<br/>
<br/>
<br/>

### 정보은닉

```Java
//잘못된 클래스 (데이터 필드 직접 조작 가능 -> 캡슐화 안됨)
class Point{
  public double x;
  public double y;
}
```

```java
//getter, setter를 이용한 데이터 캡슐화
class Point {
  private double x;
  private double y;

  public Point(doyble x, double y){
    this.x = x;
    this.y = y;
  }

  //getter, setter 생략...

}
```

```java
//java.awt.Point class는 잘못 설계되었다!
public static void main(String[] args) throws IOException {
     java.awt.Point p1 = new java.awt.Point();
     p1.x = 1;
     p1.y = 4;
     System.out.println(p1.x); //1
     System.out.println(p1.y); //4
 }
```

<br/>
<br/>
<br/>

### package-private class / private nested class

위의 클래스에서의 데이터 필드는 공개되더라도 잘못이라 말할 수 없다.

클래스 내부 표현을 변경하더라도 패키지 외부 코드는 변경되지 않을 것이다.

private nested class의 경우, 그 클래스의 바깥 클래스 외부의 코드는 아무 영향도 받지 않을 것이다.


<br/>
<br/>
<br/>
