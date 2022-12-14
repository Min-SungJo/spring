# spring

#### 템플릿 메소드 패턴 (template method pattern)
UserDao 슈퍼 클래스에 기본적인 로직의 흐름(커넥션 가져오기, SQL생성, 실행, 반환)을 만들고, 그 기능의 일부를 추상 메소드나 오버라이딩이 가능한 protec 메서드 등으로 만든 뒤 서브 클래스에서 이런 메소드를 필요에 맞게 구현해서 사용하도록 하는 방법
(주석처리된 내용)
getConnection()을 서브 클래스에서 정의
스프링에서 애용되는 디자인 패턴
#### 팩토리 메소드 패턴 (factory method pattern)
서브 클래스에서 구체적인 오브젝트 생성 방법을 결정하게 하는 것
UserDao 를 abstract class 로 하여 abstract Connect getConnection() 으로 만든다(구현코드 제거, 추상메서드로)
메소드의 구현은 서브클래스가 담당
public class NUserDao extends UserDao {
public Connection getConnection()
}

UserDao 에서 모든 기능을 엮을 필요는 없다.

### 리팩토링
기존의 코드를 외부의 동작방식에는 변화 없이 내부 구조를 변경해서 재구성하는 작업 또는 기술, 개발자는 적절한 리팩토링을 수행해야 한다.

유지보수하기 용이, 코드의 품질 상승

### 개방 폐쇄 원칙
객체지향 설계 원칙 중 하나,
클래스나 모듈 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.

UserDao 에 빗대어 보면, 자신의 핵심 기능은 변경되지 않으며 여러 종류의 DB에 접근할 수 있도록 연결 방법이라는 기능을 확장하는 데는 열려있다.

### 높은 응집도와 낮은 결합도
#### 높은 응집도 : 하나의 모듈, 클래스가 하나의 책임 또는 관심사에만 집중되어 있다
(불필요하거나 직접 관련이 없는 외부의 관심과 책임이 얽혀 있지 않다)
(하나의 공통 관심사는 한 클래스에 모여 있다)
<p>예시 : Connection 기능을 점검하기 위해 모든 DAO 를 테스트할 필요가 없다</p>
#### 낮은 결합도
결합도가 낮아지면 변화에 대응하는 속도가 높아지고 구성이 깔끔해진다. 그리고 확장하기 편리하다
(결합도 : 하나의 오브젝트가 변경이 일어날 때에 관계를 맺고 있는 다른 오브젝트에게 변ㄹ하를 요구하는 정도)
<p>예시 : Connection 인터페이스를 도입하여 DB 연결 기능을 구현한 클래스가 바뀌어도 DAO 의 코드를 변경할 필요가 없다</p>

### 전략패턴
필요에 따라 변경이 필요한 알고리즘을 인터페이스르 통해 통째로 외부로 분리시키고, 이를 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔서 사용할 수 있게 하는 디자인 패턴

### 팩토리
객체의 생성방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 오브젝트

### 제어의 역전
일반적인 프로그램의 흐름은 main()메소드와 같이 프로그램이 시작되는 지점에서 다음에 사용할 것을 결정, 오브젝트 생성, 호출 등의 작업이 반복된다.
(사용하는 쪽에서 제어하는 구조)

제어의 역전이란 이런 제어 흐름의 개념을 거꾸로 뒤집는 것. (관심을 분리하고 책임을 나누고 유연하게 확장 가능한 구조로 만드는 것)

## 스프링IoC의 용어정리
1. 빈 bean : 스프링이 IoC 방식으로 관리하는 오브젝트(그 중 스프링이 직접 그 생성과 제어를 담당하는 오브젝트만을 지칭)
2. 빈 팩토리 bean factory : 스프링의 IoC 핵심 컨테이너, 빈의 등록, 생성, 조회 및 돌려주기, 그 외에 부가적인 빈을 관리하는 기능
3. 애플리케이션 컨텍스트 application context : 빈 팩토리를 확장한 IoC 컨테이너+ 스프링이 제공하는 애플리케이션 지원기능
4. 설정정보/설정 메타정보 configuration metadata : 애플리케이션 컨텍스트 또는 빈 팩토리가 IoC를 적용하기 위해 사용하는 메타정보 (configuration)
5. 컨테이너 또는 IoC 컨테이너 container : Ioc 방식으로 빈을 관리하한다는 의미를 지녔다 
6. 스프링 프레임워크 : 스프링이 제공하는 모든 기능을 말하며 이를 줄여서 스프링이라고 부르기도 한다

### @Configuration 어노테이션과 싱글톤
이를 추가하여 스프링의 애플리케이션 컨텍스트를 통해 팩토리를 사용하는 이유 -> 서버 부하 방지

(사용자의 요청을 담당하는 여러 스레드에서 하나의 오브젝트를 공유해 동시에 사용)
#### 싱글톤 패턴 (Singleton Pattern)
어떤 클래스를 애플리케이션 내에서 제한된 인스턴스 개수, 이름처럼 주로 하나만 존재하도록 강제하는 패턴
(애플리케이션 내에서 전역적으로 접근이 가능하다)
(생성자를 private 로 하여 클래스 밖에서 오브젝트를 생성하지 못하게 한다.) (final)

#### 싱글톤 레지스트리 (Singleton Registry)
직접 싱글톤 형태의 오브젝트를 만들고 관리하는 기능

### 싱글톤과 오브젝트 
기본적으로(예외는 있다) 싱글톤이 멀티스레드 환경에서 서비스 형태의 오브젝트로 사용되는 경우에는 상태정보를 내부에 갖고 있지 않는 무상태방식으로 만들어야 한다.
저장할 공간이 하나뿐이니 다른 사용자의 정보를 불러올 수 있기 때문이다.
