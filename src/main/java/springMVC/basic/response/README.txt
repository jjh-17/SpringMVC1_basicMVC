[HTTP 응답]
1. 정적 리소스
    -정적 HTML, css, js 등을 제공할 때 사용
    -스프링 부트는 ['/static', '/public', '/resources', '/META-INF/resources'] 디렉토리에 있는 정적 리소스 제공

2. 뷰 템플릿 사용
    -동적인 HTML 제공 시 사용
    -뷰 템플릿을 거쳐 HTML 생성, 뷰가 응답을 만들어서 전달
    -스프링 부트 기본 뷰 템플릿 경로 : 'src/main/resources/templates'

3. HTTP 메시지 사용
    -HTTP API를 제공할 때 사용