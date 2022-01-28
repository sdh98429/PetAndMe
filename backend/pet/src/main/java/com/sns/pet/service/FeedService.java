package com.sns.pet.service;

import com.sns.pet.dto.FeedDto;

public interface FeedService {
    public boolean feedAdd(FeedDto feedDto) throws Exception;
    public FeedDto feedDetails(int feedNumber) throws Exception;
    public boolean feedModify(FeedDto feedDto) throws Exception;
    public boolean feedRemove(int feedNumber) throws Exception;

}
