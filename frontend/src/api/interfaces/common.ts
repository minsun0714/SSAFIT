import type { MemberInfoResponse } from './response'

export interface Member {
  memberId: string
  name: string
  nickname: string
  password: string
}

export interface Exercise {
  exerciseDate: Date
  exerciseType: string
  exerciseTime: number
}

export interface Follow {
  followingList: MemberInfoResponse[]
  followerList: MemberInfoResponse[]
}

type VideoPart = 'Run' | 'Strength' | 'Relax'
export type VideoStatus = 'APPROVED' | 'PENDING' | 'REJECTED'
export type VideoSortType = 'VIEW_CNT' | 'RECENT' | 'RATING'

export interface Video {
  videoId: string
  channelTitle: string
  title: string
  viewCount: number
  publishedAt: string
  part: VideoPart
  videoStatus: VideoStatus
  thumbnailUrl: string
}

export interface LikeVideos {
  likeId: number;
  memberId: string;
  videoId: string;
  likedAt: string;
}
