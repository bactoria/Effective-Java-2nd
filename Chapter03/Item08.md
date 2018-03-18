## Item 08 : equals를 재정의할 때는 일반 규약을 따르라
(Obey the general contract when overriding equals)

<br/>
<br/>
<br/>

### equals() 를 재정의(overriding) 안해도 될 때

<br/>

1. 각각의 객체가 고유(unique)할 때

<br/>

2. 클래스에 "논리적 동일성(logical equality)" 검사 방법이 있건 없건 상관없을 때

<br/>

3. 상위 클래스에서 재정의한 equals()를 하위클래스에서 사용해도 문제없을 때

<br/>

4. 클래스가 private또는 package-private로 선언되었고, equals()를 호출할 일이 없을 때

<br/>
<br/>
<br/>

### euqals()를 재정의 해야할 때

<br/>

1. 객체 동일성(object equality)이 아닌 논리적 동일성(logical equality)의 개념을 지원하는 클래스일 때

<br/>

2. 상위 클래스의 equals가 하위 클래스의 필요를 충족하지 못할 때

<br/>
<br/>
<br/>

### `equals()`는 동치관계
```java
x != null, y != null, z != null

  // 1. 반사성 (reflexive)
  x.equals(x) == true


  // 2. 대칭성 (symmetric)
  x.equals(y) == y.eqauls(x)


  // 3. 추이성 (transitive)
  if (x.equals(y) && y.eqauls(z))   
    System.out.println(x.equals(z)) //true


  // 4. 일관성 (consistent)
  if(x.equals(y)){

    //...생략 (x,y에 equals()에 비교되는 정보 변화 없다고 가정)

    System.out.println(x.equals(y)) //true
  }

  // 5. Non-nullity
  System.out.println(x.equals(null)) //false


```

<br/>
<br/>
<br/>



### 훌륭한 `equals()`를 구현하기 위해 따라야 할 지침

( 아래의 코드와 같이 보자 )
<br/>

1. `==` 연산자를 사용하여 eqals의 인자가 자기 자신인지 검사하라. (최적화)

<br/>

2. `instanceof` 연산자를 사용하여 인자의 자료형이 정확한지 검사하라.

<br/>

3. equals의 인자를 정확한 자료형으로 변환하라.

<br/>

4. "중요" 필드 각각이 인자로 주어진 객체의 해당 필드와 일치하는지 검사한다.

<br/>

5. `equals()` 구현을 끝냈다면 `대칭성`,`추이성`,`일관성`이 만족되는지 테스트하라.

<br/>

```Java

    @Override
    public boolean equals(Object o) {

        // 1.
        if (this == o) return true;

        // 2. instanceof 랑 같음
        if (o == null || getClass() != o.getClass()) return false;

        // 3.
        Posts posts = (Posts) o;

        // 4.
        return areaCode == posts.areaCode &&
               prefix == posts.prefix &&
               lineNumber == posts.lineNumber;
    }
```



<br/>
<br/>
<br/>

### `equals()` 구현할 때 주의사항

<br/>

1. `equals()`를 구현할 때는 hashCode도 재정의하라.

<br/>

2. 너무 머리 쓰지 마라. 필드들만 보고 동치성을 검사하면 equals 규약을 준수하기가 수월하다.

<br/>

3. `equals()`의 인자 형을 Object에서 다른 것으로 바꾸지 마라. (object.class 인자가 object임)

제너레이트 쓰자~~
