package manga.service;

import manga.model.Actualiter;
import manga.model.Manga;
import manga.repository.ActualiterRepository;
import manga.repository.MangaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

class MangaServiceTest {

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private ActualiterRepository actualiterRepository;

    @InjectMocks
    private MangaService actualiterService;

    @Test
    void testAjoutActu_Success() throws Exception {
        MockitoAnnotations.initMocks(this);

        String actu = "Actualité Test";
        int idManga = 1;

        Manga existingManga = new Manga();
        existingManga.setId(idManga);

        when(mangaRepository.findById(idManga)).thenReturn(Optional.of(existingManga));

        Actualiter actualiter = new Actualiter();
        actualiter.setActualiter(actu);
        actualiter.setActuDate(new Date());

        when(actualiterRepository.save(any(Actualiter.class))).thenReturn(actualiter);

        Actualiter result = actualiterService.ajoutActu(actu, idManga);

        verify(mangaRepository, times(1)).findById(idManga);
        verify(actualiterRepository, times(1)).save(any(Actualiter.class));

        Assertions.assertEquals(actu, result.getActualiter());
        Assertions.assertNotNull(result.getActuDate());
        Assertions.assertEquals(existingManga, result.getManga());
    }
}
