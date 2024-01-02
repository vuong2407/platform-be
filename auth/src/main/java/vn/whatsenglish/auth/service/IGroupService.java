package vn.whatsenglish.auth.service;

import vn.whatsenglish.auth.entity.Group;

public interface IGroupService {
    Group findGroupByCode(String code);
}
