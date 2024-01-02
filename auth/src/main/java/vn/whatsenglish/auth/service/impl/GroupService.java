package vn.whatsenglish.auth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.entity.Group;
import vn.whatsenglish.auth.repository.GroupRepository;
import vn.whatsenglish.auth.service.IGroupService;

@Service
@AllArgsConstructor
public class GroupService implements IGroupService {

    private GroupRepository groupRepository;

    @Override
    public Group findGroupByCode(String code) {
        return groupRepository.findByGroupCode(code).orElseThrow(() -> new RuntimeException("find group fail"));
    }
}
