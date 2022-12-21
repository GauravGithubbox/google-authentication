package com.lb.google.authentication.config;

import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthenticationTokenExtractor.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationTokenExtractorTest {
    @Autowired
    private AuthenticationTokenExtractor authenticationTokenExtractor;

    @Test
    public void testConvert() {
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn("https://example.org/example");
        Authentication actualConvertResult = authenticationTokenExtractor.convert(httpServletRequestWrapper);
        assertTrue(actualConvertResult.getAuthorities().isEmpty());
        assertFalse(actualConvertResult.isAuthenticated());
        assertEquals("https://example.org/example", actualConvertResult.getPrincipal());
        assertEquals("/example.org/example", actualConvertResult.getCredentials());
        verify(httpServletRequestWrapper, atLeast(1)).getHeader((String) any());
    }
}

