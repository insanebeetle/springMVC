package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static  MemberRepository getInstance(){
        return instance;
    }
    //싱글톤 인스턴스 받기용 겟

    private MemberRepository(){
    } //싱글톤이므로 멋대로 객체생성 못하게 막기

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    //멤버 저장용 메소드, id입력, 스토어저장, 멤버리턴
    public Member findById(Long id){
        return store.get(id);
    }
    //멤버 조회 아이디를 파라메터로 스토어에서 멤버 반환
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    //모든멤버 찾기- 멤버를 리스트 형식으로 반환 이를 부를때 store.value()를 사용

    public void clearStore(){
        store.clear();
    }

}
