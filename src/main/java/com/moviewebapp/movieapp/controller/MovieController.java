package com.moviewebapp.movieapp.controller;


import com.moviewebapp.movieapp.entity.Movie;
import com.moviewebapp.movieapp.entity.MovieDto;
import com.moviewebapp.movieapp.entity.Watchlist;
import com.moviewebapp.movieapp.repositoryservice.MovieRepository;
import com.moviewebapp.movieapp.repositoryservice.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping({"", "/"})
    public String showMovieList(Model model) {
        List<Movie> movies = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("movies", movies);
        return "movies/index";
    }

    @GetMapping("/create")
    public String showcreateMoviePage(Model model) {
        MovieDto movieDto = new MovieDto();
        model.addAttribute("movieDto", movieDto);
        return "movies/create_movie";
    }

    @PostMapping("/create")
    public String createMovie(
            @Valid @ModelAttribute MovieDto movieDto,
            BindingResult result
    ) {

        if (movieDto.getImageFileName().isEmpty()) {
            result.addError(new FieldError("movieDto", "imageFileName", "The image file is required"));
        }


        if (result.hasErrors()) {
            return "movies/create_movie";
        }


        // save image file
        MultipartFile image = movieDto.getImageFileName();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }


        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDirector(movieDto.getDirector());
        movie.setCategory(movieDto.getCategory());
        movie.setRate(movieDto.getRate());
        movie.setCreatedAt(createdAt);
        movie.setImageFileName(storageFileName);

        repository.save(movie);


        return "redirect:/movies";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model,
                               @RequestParam int id) {

        try {
            Movie movie = repository.findById(id).get();
            model.addAttribute("movie", movie);

            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movie.getTitle());
            movieDto.setDirector(movie.getDirector());
            movieDto.setCategory(movie.getCategory());
            movieDto.setRate(movie.getRate());

            model.addAttribute("movieDto", movieDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/movies";
        }

        return "movies/edit_movie";
    }

    @PostMapping("/edit")
    public String updateMovie(Model model,
                              @RequestParam int id,
                              @Valid @ModelAttribute MovieDto movieDto,
                              BindingResult result) {

        try {
            Movie movie = repository.findById(id).get();
            model.addAttribute("movie", movie);

            if (result.hasErrors()) {
                return "edit_movie";
            }

            if (!movieDto.getImageFileName().isEmpty()) {
                // delete old image
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + movie.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }

                // save new image file
                MultipartFile image = movieDto.getImageFileName();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                movie.setImageFileName(storageFileName);
            }

            movie.setTitle(movieDto.getTitle());
            movie.setDirector(movieDto.getDirector());
            movie.setCategory(movieDto.getCategory());
            movie.setRate(movieDto.getRate());

            repository.save(movie);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/movies";
    }

    @GetMapping("/delete")
    public String deleteMovie(
            @RequestParam int id
    ) {

        try {
            Movie movie = repository.findById(id).get();


            // delete student image
            Path imagePath = Paths.get("public/images/" + movie.getImageFileName());

            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }


            // delete the product
            repository.delete(movie);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/movies";

    }

    @GetMapping("/watchlist")
    public String watchlist() {

        return "movies/watchlist";
    }




}
