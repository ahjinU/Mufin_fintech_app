import { Ranking } from '@/components';
import Image from 'next/image';
import useStockStore from '../../_store';
import { commaNum } from '@/utils/commaNum';

export default function Ranks() {
  const { ranks, myRank } = useStockStore.getState();

  return (
    <div className="mt-[-1.2rem] flex flex-col gap-[0.5rem]">
      <div className="flex flex-col gap-[0.5rem]">
        {ranks.slice(0, 3).map((rank, index) => {
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
      <div className="flex flex-col gap-[0.5rem] justify-center items-center">
        {ranks.slice(3, 10).map((rank, index) => {
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
          rank={myRank.rank}
          name={myRank.childName}
          chocochip={commaNum(myRank.balance)}
        />
      </div>
    </div>
  );
}
