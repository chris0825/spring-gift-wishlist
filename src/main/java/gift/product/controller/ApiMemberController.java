package gift.product.controller;

import gift.product.service.MemberService;
import gift.product.util.CertifyUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

        return memberService.signUp(request);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        System.out.println("[ApiMemberController] login()");

        return memberService.login(request);
    }
}
