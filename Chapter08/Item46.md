## Item 46 : for 문보다는 for-each 문을 사용하라

<br/>
<br/>

### 요약
**for-each문을 사용할 수 있다면, 사용하자!**

&nbsp;
&nbsp;

```Java
List<Integer> list = Arrays.asList(1,2,3,4,5);
```

**for 문**

```java

for(int i = 0 ; i < list.length; i++) {
  System.out.println(list.get(i));
}

```

**for-each 문**

```java
for(Integer number : list) {
  System.out.println(number);
}
```
**for-each 문** 은 자바1.5 부터 사용할 수 있으며,

Iterable 인터페이스를 구현하는 클래스들과 배열은 **for-each 문** 을 사용할 수 있다.

&nbsp;
&nbsp;

### for-each를 사용하는 이유가 뭘까?

두가지를 생각해볼 수 있다.

&nbsp;

#### 1. 성능

위와같은 경우에서는

데이터가 적어서 속도차이를 못느끼겠지만,

**for-each 문** 이 더 빠르다고 할 수 있다.

**for-each** 문은 hasNext() 와 next() 를 이용하여 반복기가 만들어지는데, 다음원소 접근시간이

**for 문** 에서의 `list.get(i)` 보다 빠르기 때문이다.

&nbsp;

#### 2. 사이드이펙트

**for 문** 안에 Iterator를 넣을 수도 있다.

그러면 성능상 차이가 없어보인다.

허나, **for-each문** 이 명료하여 버그 발생 가능성이 적다.

**for 문**

```java
enum Face {ONE, TWO, THREE, FOUR, FIVE, SIX}

Collection<Face> faces = Arrays.asList(Face.values());

//for 문
for (Iterator<Face> i = faces.iterator(); i.hasNext();) {
  Face faceI = i.next();
  for (Iterator<Face> j = faces.iterator(); j.hasNext();)
    System.out.println(faceI + " " + j.next());
}
```

&nbsp;

**for-each 문**
```java
enum Face {ONE, TWO, THREE, FOUR, FIVE, SIX}

Collection<Face> faces = Arrays.asList(Face.values());

//for-each 문
for(Face i : faces)
  for(Face j : faces)
    System.out.println(i + " " + j);

```

&nbsp;
&nbsp;

### for-each를 사용하지 못하는 경우

**for-each 문** 이 더 좋으니 **for 문** 이 무쓸모해 보이지만,

경우에 따라 **for-each 문** 을 사용하지 못할 수 있다.

#### 1. 필터링

값을 삭제하기 위해서는 반복자나 배열 첨자가 필요하다

#### 2. 변환

값을 변환하기 위해서는 반복자나 배열 첨자가 필요하다

#### 3. 병렬 순회

여러 컬렉션을 병렬적으로 순회해야 한다면 반복자나 배열첨자가 필요하다
