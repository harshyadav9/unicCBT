package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;

@Repository
public interface ConfigRepository extends JpaRepository<Config, ConfigId> {

}
