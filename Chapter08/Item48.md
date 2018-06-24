## Item 48 : 정확한 답이 필요하다면 float와 double은 피하라

&nbsp;
&nbsp;

float와 double을 사용하면 나눌 때 정확한 값이 안나온다.

우리의 계산법은 10진법인데, 컴퓨터는 2진법으로 계산하기 때문에.. 안맞잖아

bigDecimal을 사용해야 한다. 이정도로만 알고있다.

&nbsp;

### 요약

**정확한 답을 요구할 때는 float나 double을 쓰지 말라.**

**수치 데이터가 십진수 9개 이하 : int 사용**

**수치 데이터가 십지수 18개 이하 : long 사용**

**수치 데이터가 십진수 19개 이상 : BigDecimal 사용**


&nbsp;
&nbsp;

**1 - 0.1 * 9 = ?**

```Java
System.out.println(1 - 0.1 * 9 );

//기대값 : 0.1
//결과값 : 0.09999999999999998
```

&nbsp;

### 돈 계산할 때는 BigDecimal, int, long 을 사용하자

```java

    public static void main(String[] args) {

        final double TEN_CENTS = 0.1;
        final double FUNDS = 0.3;

        int count = 0;

        for( double price = TEN_CENTS; FUNDS >= price; price += TEN_CENTS) {
            count++;
        }

        System.out.println(count);

        // 기대값 : 3
        // 결과값 : 2
    }

```

위와 같은 이슈를 해결하기 위해서는

**BigInteger, int, long** 을 사용하여 해결할 수 있다.

&nbsp;

**BigInteger**

```java


    public static void main(String[] args) {

        final BigDecimal TEN_CENTS = new BigDecimal("0.1");
        final BigDecimal FUNDS = new BigDecimal("0.3");

        int count = 0;

        for( BigDecimal price = TEN_CENTS ; FUNDS.compareTo(TEN_CENTS) >= 0 ; price = price.add(price) ) {
            count++;
        }

        System.out.println(count);

        // 기대값 : 3
        // 결과값 : 3   
    }

```

&nbsp;

**int**

```java
    public static void main(String[] args) {

        final int TEN_CENTS = 100;
        final int FUNDS = 300;

        int count = 0;

        for (int price = TEN_CENTS; FUNDS >= price ; price += TEN_CENTS) {
            count++;
        }

        System.out.println(count);

        //기대값 : 3
        //결과값 : 3
    }

```


long은 int와 같으니까 생략함!
