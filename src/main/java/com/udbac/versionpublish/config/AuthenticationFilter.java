package com.udbac.versionpublish.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final UsersService usersService;
//    private final String TOKEN_SECRET = "h4of9eh48vmg02nfu30v27yen295hfj65";
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, UsersService usersService) {
//        this.usersService = usersService;
//        super.setAuthenticationManager(authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
//        throws AuthenticationException {
//        try {
//
//            LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
//
//            return getAuthenticationManager().authenticate(
//                new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
//        Authentication auth) throws IOException, ServletException {
//
//        // Get User Details from Database
//        String userName = ((User)auth.getPrincipal()).getUsername();
//        UserDto userDto = usersService.getUserByEmail(userName);
//
//        // Generate GWT
//        String token = Jwts.builder().setSubject(userDto.getUserId())
//            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("3600000")))
//            .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
//
//        res.addHeader("Token", token);
//        res.addHeader("UserID", userDto.getUserId());
//    }
}
