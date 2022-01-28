package com.sns.pet.service;

import com.sns.pet.dto.FeedDto;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService{
    @Override
    public boolean feedAdd(FeedDto feedDto) throws Exception {
        return false;
    }

    @Override
    public FeedDto feedDetails(int feedNumber) throws Exception {
        return null;
    }

    @Override
    public boolean feedModify(FeedDto feedDto) throws Exception {
        return false;
    }

    @Override
    public boolean feedRemove(int feedNumber) throws Exception {
        return false;
    }
}
