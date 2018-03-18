## Item 13 : 클래스와 멤버의 접근 권한을 최소화하라

<br/>
<br/>

>잘 설계된 모듈과 그렇지 못한 모듈을 구별 짓는 가장 중요한 속성 하나는 모듈 내부의 데이터를 비롯한 구현 세부사항을 다른 모듈에 잘 감추느냐의 여부다.

### 요약
**1. 접근권한은 가능한 낮추라**
**2. 최소한의 public API를 설계한 다음, 다른 모든 클래스, 인터페이스, 멤버는 API에서 제외하라**
**3. public static final 필드를 제외한 어떤 필드도 public 필드로 선언하지 마라**
**4. public static final 필드가 참조하는 객체는 변경 불가능 객체로 만들라**

<br/>
<br/>
<br/>

### 정보은닉

모듈 사이의 의존성을 낮추자

각자 개별적으로 개발, 테스트, 유지보수 등 수행

다른 모듈에 영향을 끼치지 않고 디버깅 가능

성능을 보장하진 않지만 효과적인 성능튜닝을 가능하게 한다.
-> 시스템 구축 후 어떤 모듈이 성능문제를 일으키는지 프로파일링 하기 용이하기 때문.(규칙 55)

자바에서는 정보 은닉을 실현하는데 있어

`private`, `protected`, `public` 등 권한수정자의 적절한 사용은 정보 은닉 원칙을 실현하는 핵심적인 부분이다.

<br/>
<br/>
<br/>

### API 설계

개발 중인 소프트웨어의 정상적인 동작을 보증하는 한도 내에서 가장 낮은 접근권한을 설정하라.

API를 개발한다고 하자.

<br/>

#### `클래스 권한`

<br/>

`package-private`  

API의 일부가 아니라 구현 세부사항에 속함

다음번 릴리스에 클라이언트 코드를 깨트릴 걱정 없이 자유로이 변경/삭제/대체 가능

만약 class를 사용하는 클래스가 하나 뿐이라면?!
-> 그 클래스의 private 중첩 클래스로 구현을 고려해라. (내부클래스로 만들어라)

<br/>

`public`  

해당 패키지 API의 일부다.

호환성을 보장하기 위해 해당 개체를 계속 지원해야 한다.

<br/>
<br/>

#### `필드, 메서드, 중첩클래스. 중첩인터페이스 권한`

<br/>

`private`

선언된 최상위 레벨 클래스 내부에서만 사용 가능.

<br/>

`package-private`

 같은 패키지 내의 아무 클래스나 사용 가능. (Default)

 <br/>

`protected`

선언된 클래스 및 하위 클래스만 사용 가능.
protected 멤버는 해당 클래스의 공개 API이며, 영원히 유지해야 한다. protected 멤버 사용은 자제해야 한다.

<br/>

`public`

어디에서나 사용 가능.

<br/>
<br/>

**Serializable을 구현하는 클래스의 멤버는
private, package-private 라도 공개API속으로 새어나갈(leak) 수도 있다.(규칙 74, 규칙 75)**

<br/>


<br/>

**상위 클래스 객체를 사용할 수 있는 곳에는 하위 클래스 객체도 사용할 수 있어야 한다.**

어떤 메서드를 override 했을 때,

상위 클래스 메서드 보다 낮은 권한을 설정할 수 없다.

public 메서드를 override했으면

이것도 public이어야 한다.

-> 어기면 컴파일에러 발생함


<br/>
<br/>
<br/>

### field를 public 선언하면 안된다. (규칙14)
1. 불변식을 강제할 수 없다.
2. 다중 스레드에 안전하지 않다.
3. 공개API의 일부가 되어버리므로, 삭제하거나 수정할 수 없다.

<br/>
<br/>
<br/>

### public static final 필드
public static final 필드들은 반드시

기본 자료형 값들을 갖거나, 변경 불가능 객체를 참조해야 한다.(규칙 15)

변경가능 객체를 참조하는 final 필드는 non-final 필드의 단점들을 그대로 갖는다.
<br/>

```Java
//보안 문제를 초래할 수 있는 코드 - muttable 객체를 참조한다.
public static final Thing[] VALUES = { ... };
```

```Java
// 해결법 1.
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES =
  Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```


```Java
// 해결법 2.
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
  return PRIVATE_VALUES.clone();
}
```


<br/>
<br/>
<br/>
