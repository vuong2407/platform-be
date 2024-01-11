package vn.whatsenglish.backend.service;

import vn.whatsenglish.backend.entity.Group;

public interface IGroupService {
    Group findGroupByCode(String code);
}
