# Item 56 : 일반적으로 통용되는 작명 관습을 따르라

&nbsp;
&nbsp;

### 작명 관습


**package** - `com.google.injet`, `org.joda.time.format`

- 각 컴포넌트는 `.` 로 구분
- 각 컴포넌트는 하나의 단어나 약어
- 최상위 도메인 이름 순
- 알파벳 소문자로 구성
- 숫자는 거의 사용 X
- 단, 표준 라이브러리 명은 java와 javax로 시작. 
- 사용자는 java, javax로 시작하는 패키지 만들지 못함

&nbsp;

**class(Interface)** - `Timer`, `FutureTask`, `LinkedHashMap`, `HttpServlet`

- 각 단어의 첫 글자는 대문자
- 널리 쓰이는 약어(min, max 등) 가 아니라면 약어 사용X
- ex) `HTTPURL` / `HttpUrl` 둘 다 사용 가능.

&nbsp;

**method** - `Remove`, `ensureCapacity`, `getCrc`

- 첫 글자는 소문자
- 널리 쓰이는 약어(min, max 등) 가 아니라면 약어 사용X

&nbsp;

**constant field** - `MIN_VALUE`, `NEGATIVE_INFINITY`

constant field : static final 필드 이거나 변경 불가능한 참조 자료형

- 대문자 사용
- 띄어쓰기는 `_` 로 표기

&nbsp;

**local variable** - `i`, `xref`, `houseNumber`

- 첫 글자 소문자
- camel case
- 약어 사용 가능

&nbsp;

**data type parameter** - `T`, `E`, `K`, `V`, `X`, `T1`, `T2`

- 보통 하나의 대문자
- 임의의 자료형 (T)
- 연속적인 임의의 자료형 T,U,V 또는 T1,T2,T3
- 컬렉션의 요소 자료형 (E)
- 맵의 키(K) 와 값(V)

&nbsp;
&nbsp;

**표준적 작명 관습을 내면화 시키고, 마치 제2의 천성인 것처럼 사용하라**