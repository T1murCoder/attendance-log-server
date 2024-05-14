package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.controller.dto.GroupWithoutStudentsDto;
import ru.t1murcoder.domain.Group;

import java.util.stream.Collectors;

@UtilityClass
public class GroupMapper {

    public GroupDto toGroupDto(Group group) {

        GroupDto groupDto = GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .build();

        if (group.getStudentList() != null) groupDto.setStudentList(group.getStudentList()
                .stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList())
        );

        return groupDto;
    }

    public GroupWithoutStudentsDto toGroupWithoutStudentsDto(Group group) {

        GroupWithoutStudentsDto groupDto = GroupWithoutStudentsDto.builder()
                .id(group.getId())
                .name(group.getName())
                .build();


        return groupDto;
    }

    public Group toGroupEntity(GroupDto groupDto) {

        Group group = Group.builder()
                .name(groupDto.getName())
                .build();

        if (groupDto.getId() != null) group.setId(groupDto.getId());
        if (groupDto.getStudentList() != null) group.setStudentList(groupDto.getStudentList()
                .stream()
                .map(StudentMapper::toStudentEntity)
                .collect(Collectors.toList())
        );

        return group;
    }

    public Group toGroupEntity(GroupWithoutStudentsDto groupDto) {
        Group group = Group.builder()
                .name(groupDto.getName())
                .build();

        if (groupDto.getId() != null) group.setId(groupDto.getId());

        return group;
    }

}
