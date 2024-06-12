package com.example.focus.focussession.service;

import com.example.focus.focussession.repository.FocusSessionTagRepository;
import org.springframework.stereotype.Service;

@Service
public class FocusSessionTagServiceImpl implements FocusSessionTagService{
    private final FocusSessionTagRepository focusTagRepository;

    public FocusSessionTagServiceImpl(FocusSessionTagRepository focusTagRepository) {
        this.focusTagRepository = focusTagRepository;
    }


}
