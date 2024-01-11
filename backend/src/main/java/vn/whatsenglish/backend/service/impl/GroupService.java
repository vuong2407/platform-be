package vn.whatsenglish.backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.whatsenglish.backend.entity.Group;
import vn.whatsenglish.backend.repository.GroupRepository;
import vn.whatsenglish.backend.service.IGroupService;

@Service
@AllArgsConstructor
public class GroupService implements IGroupService {

    private GroupRepository groupRepository;

    @Override
    public Group findGroupByCode(String code) {
        return groupRepository.findByGroupCode(code).orElseThrow(() -> new RuntimeException("find group fail"));
    }
}
