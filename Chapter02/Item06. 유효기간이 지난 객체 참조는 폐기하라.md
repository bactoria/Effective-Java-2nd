## Item 06 : 유효기간이 지난 객체 참조는 폐기하라
(Eliminate obsolete object references)

**메모리 누수(memory Leak) 방지하기**
1. 자체적을 메모리를 관리하는 클래스를 만들 때는 메모리 누수가 발생하지 않도록 해야한다.
2. static factory method
3. static initializer
4. Wrapper class

유효기간이 지난 객체 참조를 폐기하여
메모리 누수(memory Leak) 을 방지하자!

<br/>

---
## 1. 자체적을 메모리를 관리하는 클래스를 만들 때는 메모리 누수가 발생하지 않도록 해야한다.

더 이상 사용되지 않는 원소 안에있는 객체 참조는 `null`로 바꾸자

`Stack.class`

```Java
public synchronized E pop() {
    E   obj;
    int  len = size();

    obj = peek();
    removeElementAt(len - 1);

    return obj;
}

public synchronized void removeElementAt(int index) {
    modCount++;
    if (index >= elementCount) {
        throw new ArrayIndexOutOfBoundsException(index + " >= " +
                                                 elementCount);
    }
    else if (index < 0) {
        throw new ArrayIndexOutOfBoundsException(index);
    }
    int j = elementCount - index - 1;
    if (j > 0) {
        System.arraycopy(elementData, index + 1, elementData, index, j);
    }
    elementCount--;
    elementData[elementCount] = null; /* to let gc do its work */
}
```

제일 밑줄 보면 삭제할 index의 참조는 `null`로 바꾼다.

`to let gc do its work` 라고 주석처리 되어있다.

참조를 없애서 gc에게 나 이 객체 안쓰니까 메모리반납 하라는 것이다.

`null` 처리를 안해도 프로그램은 정상적으로 실행된다.

다만, 메모리누수로 이어질 수 있다는점..

앞으로 사용하지도 않을 객체인데도 불구하고 참조되고있다는 이유로

메모리 반납이 이루어지지 않기 때문

<br/>
<br/>
<br/>

## 2. 캐시도 메모리 누수가 흔히 발생하는 장소다.

객체 참조를 캐시 안에 넣어놓고 잊어버리는 일이 많기 때문.

해결책
1. WeakHashMap으로 캐시를 구현
  캐시 밖에서 key를 참조하고있을 때만 value를 보관하면 될때 사용가능.

2. 사용하지 않는 캐시 비우기
후면스레드 (Timer나 ScheduledThreadPoolExecutor 이용) or
캐시에 새로운 항목을 추가할 때 처리 (LinkedHashMap의 removeEldestEntry() 이용)


  <br/>
  <br/>
  <br/>

## 3. 메모리 누수가 흔히 발견되는 또 한 곳은 리스너 등의 역호출자다.

역호출자를 명시적으로 제거하지 않을 경우, 적절한 조치를 취하기 전까지 메모리는 점유된 상태로 남아있게 된다.

GC가 역호출자를 즉시 처리하도록 할 가장 좋은 방법은 역호출자에 대한 약한 참조(week reference) 만 저장하는 것이다. WeakHashMap의 키로 저장하는 것이 그 예다.

---

`page. 37`
만기 참조(obsolete reference)를 제거하는 가장 좋은 방법은 해당 참조가 보관된 변수가 유효범위(scope)를 벗어나게 두는 것이다. 변수를 정의할 때 그 유효범위를 최대한 좁게 만들면 자연스렵게 해결된다.(item 45)
-> 함수 안에 선언하여 참조변수 자체를 빨리 GC처리 하라는 그런 의미인 것 같다.
  객체의 생명주기도 잘 파악해야 이런 것도 제대로 할 수 있을것 같은데..

-> 만약 생명주기를 잘 알고 있어 제때 반납가능하면 HashMap으로 써도 될것 같고,
   생명주기가 애매할 경우에는 WeakHashMap을 쓰지않나 싶다.

  <br/>

`page. 38`
  메모리누수는 보통 뚜렷한 오류로 이어지지 않기 때문에, 수년간 시스템에 남아 있는 경우도 있다. 이런 문제가 생길 수 있다는 것을 사전에 인지하고 방지 대책을 세우는 것이 바람직하다.
