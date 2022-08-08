# Voard (SpringBoot / JPA / querydsl )

파일 구조 
----------------------------------------------------------------------------------------
Config
 - SecurityConfiguration
 - QuerydslConfiguration
 
Controller
 - Board
 - Login
 
Dto
 - CategoryDto
 - MemberDto
 - PostDto

Entity
 - Category (게시글 항목)
 - Member (회원)
 - Post (게시글)
 
Repository
 * Entity와 연관되어 Repository - RepositoryCustom - RepositoryImpl 구성
 
Security 
 - CustomAccessDeniedHandler -> Implement AccessDeniedHandler (권한 거부 redirect("/board")로 설정)
 - CustomAuthenticationEntryPoint -> Implement AuthenticationEntryPoint (인증 요구 redirect("/login")로 설정)

Service 
 - CategoryService
 - MemberService
 - PostService
----------------------------------------------------------------------------------------------

로그인 / 회원가입
<br><br>
 - SpringSecurity를 사용하여 인증과 권한부여를 구현하였으며, MemberService(Implement UserDetailServcie)는
 - 회원가입에 해당되는 메소드 JoinUser와 로그인 권한부여에 해당되는 메소드 @Override loadUserByUsername로 구성되어 있습니다.
 - 멤버 테이블의 column(role)에 따라서 권한을 설정하였습니다.
 - 로그인 사용자의 여부 및 ID 정보를 확인하기 위해서 Principal 객체를 사용하고 있습니다.

![login](https://user-images.githubusercontent.com/82531576/182930246-d7fcc2bf-d615-46a8-80a1-af77023251d6.PNG)

게시판 (Video Board)
<br><br>
 - 유튜브 링크를 복사하여 add input에 입력하게 되면 회원이 가지고 있는 카테고리(항목)에 따라서 저장을 할 수 있습니다.
 - 저장 된 유튜브는 밑에 유저가 설정한 카테고리와 삭제 버튼으로 구성되어 있습니다.
 - 게시글 테이블(Post)는 JoinColumn(category_id)을 기준으로 다대일 방식으로 설정되어 있습니다.
 - 페이징의 경우 Intersection Observer를 활용하여 무한스크롤 형식으로 구현되어 있습니다.

![postService](https://user-images.githubusercontent.com/82531576/182931732-3fb7a6fb-ecd0-4569-aadd-c9db45601f94.PNG)

![paging](https://user-images.githubusercontent.com/82531576/182931756-381e0288-555e-46b4-9392-8b27b70d4393.PNG)

 - 삭제나 카테고리 별로 데이터 요청 시 그리고 ajax로 데이터를 요청 시 RequestParam OR Pathvariable를 사용하여 url 파라미터를 매핑하였습니다.
 
![requsetParam](https://user-images.githubusercontent.com/82531576/182971100-ad477e53-c80f-4082-a868-4b89437ed4f8.PNG)


![pathvariable](https://user-images.githubusercontent.com/82531576/182971115-6085eb4d-6492-4748-a792-18b549a028f2.PNG)


보완 예정
 - Entity -> Dto 로 매핑하여 viewlayer 리턴 



