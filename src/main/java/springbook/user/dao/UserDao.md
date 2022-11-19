# UserDao
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