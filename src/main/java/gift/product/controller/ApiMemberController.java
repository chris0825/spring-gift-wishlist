package gift.product.controller;

import gift.product.model.Member;
import gift.product.service.MemberService;
import gift.product.util.CertifyUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    private final MemberService memberService;
    private final CertifyUtil certifyUtil;

    @Autowired
    public ApiMemberController(
        MemberService memberService, CertifyUtil certifyUtil) {
        this.memberService = memberService;
        this.certifyUtil = certifyUtil;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, String> request) {
        System.out.println("[ApiMemberController] signUp()");

        Member member = memberService.signUp(request);

        Map<String, String> response = new HashMap<>();
        response.put("token", certifyUtil.generateToken(member.getEmail()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        System.out.println("[ApiMemberController] login()");

        memberService.login(request);

        Map<String, String> response = new HashMap<>();
        response.put("token", certifyUtil.generateToken(request.get("email")));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
