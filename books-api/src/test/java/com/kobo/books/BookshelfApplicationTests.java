package com.kobo.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kobo.books.api.dto.CreateBookDto;
import com.kobo.books.api.dto.UpdateBookDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookshelfApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    public void getAllBooks() throws Exception {
        mockMvc
                .perform(
                        get("/books/")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(4));
    }

    @Test
    public void thenCreateBookWithDescAndTestVersionableMethods() throws Exception {
        mockMvc
                .perform(
                        post("/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(
                                        CreateBookDto.builder()
                                                .isbn("isbn")
                                                .author("author")
                                                .title("title")
                                                .description("description")
                                                .build()
                                        )
                                )
                )
                .andExpect(status().isOk());

        mockMvc
                .perform(
                        get("/books/isbn")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").doesNotExist());

        mockMvc
                .perform(
                        get("/v2/books/isbn")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("description"));

        mockMvc
                .perform(
                        put("/books/isbn")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(
                                        UpdateBookDto.builder()
                                                .author("author")
                                                .title("title")
                                                // no description
                                                .build()
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").doesNotExist());

        mockMvc
                .perform(
                        get("/v2/books/isbn")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("description"));
    }
}
