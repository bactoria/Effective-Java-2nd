# Item 49 : 객체화된 기본 자료형 대신 기본 자료형을 이용하라

&nbsp;
&nbsp;

**자료형**

기본 자료형 : int, double, boolean 등

참조 자료형 : String, Integer, Double, Boolean 등

&nbsp;
&nbsp;

### 기본 자료형 vs 참조 자료형

기본 자료형이 참조 자료형보다 시간이나 공간 요구량 측면에서 효율적이다.

```java
    //Auto-unboxing
    Integer f = 10;  // Integer f = new Integer(10);
```


```java
    Integer f = new Integer(10);

    // 기본 자료형과 비교할 경우에 f는 Auto-unboxing 됨
    if(f == 10) System.out.println("같음");

    // 결과 : 같음

```

```java

    Integer test1_1 = new Integer(10);
    Integer test1_2 = new Integer(10);

    if(test1_1 == test1_2) System.out.println("같음");
    else System.out.println("다름");

    //결과 : 다름


    Integer test2_1 = 10;
    Integer test2_2 = 10;

    if(test2_1 == test2_2) System.out.println("같음");
    else System.out.println("다름");

    //결과 : 같음


    String test3_1 = new String("안녕");
    String test3_2 = new String("안녕");

    if(test3_1 == test3_2) System.out.println("같음");
    else System.out.println("다름");

    //결과 : 다름


    String test4_1 = "안녕~";
    String test4_2 = "안녕~";

    if(test4_1 == test4_2) System.out.println("같음");
    else System.out.println("다름");

    // 결과 : 같음

```

여기서 `==` 는 Heap영역에 할당 된 객체의 주소가 같은지를 비교한다. 

수치가 같아도 참조값이 다르면 틀린거라는 말이다.

이건 뭐.. Basic하니까 pass

**객체화된 기본 자료형에 == 연산자를 사용하는 것은 거의 항상 오류라고 봐야 한다.**

&nbsp;

test1의 Integer 형을 비교하려면 int형에 다시 담아준 후, 비교하자

```java
    Integer test1_1 = new Integer(10);
    Integer test1_2 = new Integer(10);

    int f = first; //Auto-unboxing
    int s = second; //Auto-unboxing

    if(f==s) System.out.println("같음");
    else System.out.println("다름");

    // 결과 : 같음

```

&nbsp;

**성눙 최악 프로그램**

```java

public static void main(String[] args) {
    Long sum = 0L;
    
    for (Long i = 0 ; i < Integer.MAX_VALUE; i++) {
        sum += i;
    }
    
    System.out.println(sum);
}

```

뭐가 문젤까? 뭐가 문젤까? .. 

`sum` 은 객체를 참조하고있다.

그 객체는 0, 1, 2, 3, 4, 5, 6 ... 계속 1씩 증가하게 될까?

&nbsp;

**Long.java** 를 살펴보면

```java
    private final long value;
```

`value` 값은 `final` 이다. 불변 객체라는 말이다.

그럼 0, 1, 2, 3, 4 ... 바뀌는 동안

새로운 객체를 계속 생성하고, 그 객체를 매번 참조하게 된다.

&nbsp;

`String` 객체만 그런 줄 알고 있었는데..

`Integer`, `Long`, `Double` 까지 `Immutable Class` 이다.

`Boolean`은 어짜피 `static`이라서 뭐..

&nbsp;
&nbsp;
