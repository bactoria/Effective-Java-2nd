## Item 12 : Comparable 구현을 고려하라
(Consider implementing Comparable)

<br/>
<br/>


**`Comparable 인터페이스`를 구현하는 클래스의 객체들은 자연적 순서(natural ordering)를 갖게 된다.**

자바 플랫폼 라이브러리에 포함된 거의 모든 value class는 `Comparable 인터페이스`를 구현한다.
<br/>
<br/>
<br/>

### compareTo() 잘못짜면 비교 연산에 기반한 클래스들이 오작동할 수 있다.

TreeSet이나 TreeMap 같은 `sorted collection`

Arrays와 Collections같은 `utility class`

`탐색과 정렬 알고리즘을 포함하는 class`

-> 당연.. compareTo가 이상하면 정렬이상하게 되겠찌..


<br/>
<br/>
<br/>

### equals 와 compareTo 동치성 차이

```Java
  //hashSet
  Set<BigDecimal> hashSet = new HashSet<>();

  hashSet.add(new BigDecimal("1.0"));
  hashSet.add(new BigDecimal("1.00"));
  System.out.println(hashSet.size());  //2


  //treeSet
  Set<BigDecimal> treeSet = new TreeSet<>();

  treeSet.add(new BigDecimal("1.0"));
  treeSet.add(new BigDecimal("1.00"));
  System.out.println(treeSet.size());  //1
```

`hashSet`은 equals로 비교하고,

`treeSet`은 compareTo로 비교한다.

그 차이가 위와 같은 결과를 보인다.

```Java
System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1.00")));
//0

System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1.00")));
//false

System.out.println(new Double(1.0).equals(1.00));
//true
```

`Double`형은 true인데 `BigDecimal`형은 false네...

근데 `BigDecimal`형을 compareTo로 하면 같다고 해버리네.

<br/>
<br/>
<br/>

### compareTo()의 인자 자료형은 컴파일 시간에 정적으로 결정된다.

잘못된 자료형 객체를 인자로 넘길 경우 **컴파일에러**

비교대상인 두 객체가 서로 다른 클래스일 때 `ClassCastException` 발생.

null이 인자로 전달되는 경우 `NullPointerException` 발생시켜야 하고, 실제로 그렇다.

<br/>
<br/>
<br/>

### CompareTo() 주의할점

```java
class Node implements Comparable<Node>{

  private int x;

  @override
  public int compareTo(Node that) {
    return this.x - that.x;
  }

  ...
}  
```

여기서 `this.x - that.x` 가 `Integer.MAX_VALUE`를 넘어버리면(오버플로우) 음수가 되버린다.

이 경우에는 에러도 안뜬다. 걍 결과값이 뒤죽박죽 되버린다.

조심하자! `if문`으로 바꾸던가 `Long`으로 바꾸던가..

<br/>

근데 `int x`가 `private` 형인데 `that.x`로 불러올 수가 있더라..

자기 클래스라서 그런건가... 안될줄 알았는데

됨~~.

<br/>
<br/>
<br/>
