package com.web.lab4.controller;

import com.web.lab4.dto.DotDto;
import com.web.lab4.entity.Dot;
import com.web.lab4.logic.AreaChecker;
import com.web.lab4.repository.DotRepository;
import com.web.lab4.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DotController {

    private AreaChecker areaChecker;
    private DotRepository dotRepository;
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public DotController(AreaChecker areaChecker, DotRepository dotRepository, UserRepository userRepository) {
        this.areaChecker = areaChecker;
        this.dotRepository = dotRepository;
        this.userRepository = userRepository;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    @CrossOrigin
    @GetMapping("/dots")
    List<Dot> getAllDots(Principal user) {
        logger.info("get dots request");
        return dotRepository.findByUser(userRepository.findUserByName(user.getName()));
    }

    @CrossOrigin
    @PostMapping("/dots")
    Dot addDot(@RequestBody DotDto dto, Principal user) {

        logger.info("post dot request");

        Dot dot = new Dot(dto);
        dot.setUser(userRepository.findUserByName(user.getName()));
        dot.setResult(areaChecker.checkArea(dot) ? '1' : '0');
        dot.setDate(dateFormat.format(new Date(System.currentTimeMillis())));

        return dotRepository.save(dot);
    }

    @CrossOrigin
    @DeleteMapping("/dots")
    void deleteDots(Principal user) {
        logger.info("delete dots request");
        dotRepository.deleteAllByUser(userRepository.findUserByName(user.getName()));
    }
}
