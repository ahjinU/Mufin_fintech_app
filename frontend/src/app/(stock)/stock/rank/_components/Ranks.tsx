import { Ranking } from '@/components';
import Image from 'next/image';
import { commaNum } from '@/utils/commaNum';
import { serverGetFetch } from '@/hooks/useServerFetch';
import { RankType } from '../../_types';

export default async function Ranks() {
  const ranks = await serverGetFetch({ api: '/stock/ranking/total' });
  const ranksData = ranks.data.ranks;
  const myRank = await serverGetFetch({ api: '/stock/ranking/user' });
  const myRankData = myRank.data;

  return (
    <div className="mt-[-1.2rem] flex flex-col gap-[0.5rem]">
      <div className="flex flex-col gap-[0.5rem]">
        {ranksData.slice(0, 3).map((rank: RankType, index: number) => {
          return (
            <Ranking
              mode={'HIGHLIGHT'}
              rank={rank.rank}
              name={rank.childName}
              chocochip={commaNum(rank.balance)}
              key={index}
            />
          );
        })}
      </div>
      <div className="mt-[0.5rem] flex flex-col gap-[1rem] justify-center items-center">
        {ranksData.slice(3, 10).map((rank: RankType, index: number) => {
          return (
            <Ranking
              mode={'GENERAL'}
              rank={rank.rank}
              name={rank.childName}
              chocochip={commaNum(rank.balance)}
              key={index}
            />
          );
        })}
      </div>
      <div className="flex w-full items-center justify-center flex-col mb-[-1rem] gap-[0.5rem]">
        <Image
          src={'/images/icon-rank-dot.png'}
          width={20}
          height={20}
          alt={'icon-rank-dot'}
        />
        <Ranking
          mode={'HIGHLIGHT'}
          rank={myRankData.rank}
          name={myRankData.childName}
          chocochip={commaNum(myRankData.balance)}
        />
      </div>
    </div>
  );
}
