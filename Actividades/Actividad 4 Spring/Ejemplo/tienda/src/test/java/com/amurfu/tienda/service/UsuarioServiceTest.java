package com.amurfu.tienda.service;

import com.amurfu.tienda.data.Usuario;
import com.amurfu.tienda.data.dto.UsuarioDto;
import com.amurfu.tienda.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDto usuarioDto;

    @BeforeEach
    void setUp() {
        // Set up mock data
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");
        usuario.setApellidoPaterno("Pérez");
        usuario.setApellidoMaterno("López");
        usuario.setCorreo("juan.perez@example.com");

        usuarioDto = new UsuarioDto();
        usuarioDto.setNombre("Juan");
        usuarioDto.setApellidoPaterno("Pérez");
        usuarioDto.setApellidoMaterno("López");
        usuarioDto.setCorreo("juan.perez@example.com");
    }

    @Test
    void getUsuariosShouldReturnListOfUsuarios() {
        // Configure the mock to return a list of users
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        // Call the method to test
        List<UsuarioDto> result = usuarioService.getUsuarios();

        // Assert the results
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(usuario.getNombre(), result.get(0).getNombre());

        // Verify the interaction with the mock
        verify(usuarioRepository).findAll();
    }

    @Test
    void guardarUsuarioShouldReturnUsuarioDto() {
        // Configure the mock to return the user when saved
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(1); // Simulate generated ID
            return usuario;
        });

        // Call the method to test
        UsuarioDto savedDto = usuarioService.guardarUsuario(usuarioDto);

        // Assert the results
        assertNotNull(savedDto);
        assertNotNull(savedDto.getId());
        assertEquals(usuario.getNombre(), savedDto.getNombre());

        // Verify the interaction with the mock
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
