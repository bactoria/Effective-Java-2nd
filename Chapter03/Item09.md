## Item 09 : equals를 재정의할 때는 반드시 hashCode도 재정의하라
(Always override hashCode when you override equals)

<br/>
<br/>

### Object 클래스 명세 일반 규약[JavaSE6]

<br/>

1. 프로그램 실행 중에 객체의 hashCode를 여러 번 호출하는 경우, equals가 사용하는 정보들이 변경되지 않았다면, 언제나 동일한 정수(integer)가 반환되어야 한다. 종료 후 다시 실행시 값이 같을 필요는 없다.

<br/>

2. **`equals()` 가 같다고 판정한 두 객체의 hashCode 값은 같아야 한다.**

<br/>

3. equals() 가 다르다고 판정한 두 객체의 hashCode가 같을 수도 있다. 이 경우 hash table의 성능이 저하될 수 있다.

<br/>
<br/>
<br/>

### hashCode()를 재정의 하지 않으면 ?
```java
public final class PhoneNumber{

  private final short areaCode;
  private final short prefix;
  private final short lineNumber;

  //...생략

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return areaCode == posts.areaCode &&
               prefix == posts.prefix &&
               lineNumber == posts.lineNumber;
    }  

  //hashCode() 없음.
}
```

만약 hashCode()를 재정의 하지 않으면

아래와 같이 Hash기반 컬렉션을 정상적으로 사용할 수 없다.

```Java
  Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
  m.put(new PhoneNumber(010, 1234, 5678), "junoh"); // key, value

  //010-1234-5678 소유주 찾기
  m.get(new PhoneNumber(010, 1234, 5678)); //null (hashCode 값이 다름)

```

<br/>

따라서 hashCode를 재정의 해준다.

```Java
@Override
public int hashCode() {

    @Override public int hashCode() {

      int result = 17;

      result = 31 * result + areaCode;
      result = 31 * result + prefix;
      result = 31 * result + lineNumber;

      return result;
```
위 코드는 아래의 지침을 따랐다.
<br/>
<br/>
<br/>

### 좋은 해시함수 만들기위한 hashCode 지침

좋은 해시함수?
서로 다른 객체들을 모든 가능한 해시 값에 균등하게 배분해야 한다. 그래야 성능이 잘나오니까..
아래 지침을 따르면 이상적인 해시함수에 가까운 함수를 만들 수 있다.

1. `int result = 17;` (0만 아니면 됨.)
<br/>
<br/>
2. `equals()`에서 사용하는 필드(`f`) 들에 대해서 아래의 절차를 `반복`한다.
<br/>
  A. 해당 필드의 자료형에 따라 int 해시 코드(`c`) 를 계산한다.
     `boolean` -> (f ? 1 : 0)
     `byte`, `char`, `short`, `int` -> (int) f
     `long` -> (int) (f ^ (f>>>32))
     `float` -> Float.floatToIntBits(f)
     `double` -> Double.doubleToLongBits(f) -> c.수행
     `객체 참조(Object reference)` -> 그 객체의 `equals()` 호출
     `배열(Array)` -> 각 원소가 별도 필드인 것처럼 수행
<br/>
  B. result = 31 * result + c;
<br/>
<br/>
3. `return result;` //해시값 반환
<br/>
<br/>
4. 동치관계에 있는 객체의 hashCode 값이 같은지 점검한다. 단위테스트 이용. 버그 확인해라!

<br/>
<br/>
<br/>

###`equals()` 구현시 주의사항

<br/>

1. `equals()`를 구현할 때는 hashCode도 재정의하라.

<br/>

2. 너무 머리 쓰지 마라. 필드들만 보고 동치성을 검사하면 equals 규약을 준수하기가 수월하다.

<br/>

3. `equals()`의 인자 형을 Object에서 다른 것으로 바꾸지 마라. (object.class 인자가 object임)

<br/>
<br/>
<br/>

### Generate 쓰자~
![](../../images/Item09_01.PNG)

Generate를 쓰면 주의사항도 알아서 잘 지켜준다

덕분에 해시코드 쉽게 잘 만들 수 있을듯.

Generate 사용을 지양하라는 글은 찾지 못했다.

단점이 없는건지... 내가 못찾는건지...

Generate로 만들면 아까 만들었던 **hashCode()** 는 다음과 같이 생성된다.
```Java
@Override
    public int hashCode() {

        return Objects.hash(areaCode, prefix, lineNumber);
    }
```
타고타고 들어가보면 `result = 1` 이라는거 말고 똑같다. 순차적으로 해시값 구함
<br/>
<br/>
<br/>

중복필드는 해시코드 계산 과정에서 제외해도 된다.
-> 당연한 말. 이건  `equals()`에서 미리 제외했을듯

성능 개선하려고 객체의 중요 부분을 해시 코드 계산 과정에서 생략하면 안된다.
-> 해시값 품질이 좋지 않아 테이블 성능을 엄청 떨어트릴 수 있다. 성능 더나빠짐
ex) `JDK1.2` 이전의 String 해시함수
