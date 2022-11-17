package springbook.user.domain;
/**
 * 사용자 정보 저장
 * id, name, password 이렇게 세 개의 프로퍼티를 User 클래스
 * **/
public class User {
    String id;
    String name;
    String password;

    public String getId(){
        return id;
    }
    public void setId(){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
