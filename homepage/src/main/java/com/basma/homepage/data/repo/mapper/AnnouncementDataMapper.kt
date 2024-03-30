package com.basma.homepage.data.repo.mapper

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.entity.AnnouncementLocalEntity
import com.basma.homepage.domain.entity.Announcement
import javax.inject.Inject

class AnnouncementDataMapper @Inject constructor() : Mapper<Announcement, AnnouncementLocalEntity> {

    override fun from(i: Announcement?): AnnouncementLocalEntity {
        TODO("Not yet implemented")
    }

    override fun to(o: AnnouncementLocalEntity?): Announcement {
        TODO("Not yet implemented")
    }
}