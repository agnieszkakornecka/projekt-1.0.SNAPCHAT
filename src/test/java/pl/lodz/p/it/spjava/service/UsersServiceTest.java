package pl.lodz.p.it.spjava.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.lodz.p.it.spjava.dao.UsersRepository;
import pl.lodz.p.it.spjava.model.Users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class UsersServiceTest {

    private static final String USER_NAME = "any_name";

    @InjectMocks
    private UsersService sut = new UsersService();

    @Mock
    private UsersRepository usersRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldFindUserByName() {

        //given
        Users user = mock(Users.class);
        given(user.getUsername()).willReturn(USER_NAME);
        given(usersRepository.getUsersByUsername(USER_NAME)).willReturn(user);

        //when
        Users result = sut.getUsersByUsername(USER_NAME);

        //then
        assertEquals(USER_NAME, result.getUsername());

    }

    @Test
    public void shouldNotFindUserIfUsernameIsNull() {

        //given
        String userName = null;

        //when
        Users result = sut.getUsersByUsername(userName);

        //then
        assertTrue(result == null);
        verifyZeroInteractions(usersRepository);
    }
}