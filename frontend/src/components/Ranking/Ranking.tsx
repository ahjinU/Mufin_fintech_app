import Image from 'next/image';
import { SparklesIcon } from '@heroicons/react/24/solid';

interface RankingProps {
  mode: 'HIGHLIGHT' | 'GENERAL';
  rank: number;
  name: string;
  chocochip: string;
}

export default function Ranking({ mode, rank, name, chocochip }: RankingProps) {
  const topRank = rank === 1 || rank === 2 || rank === 3;
  let backgroundClass: string;
  let nameClass: string;
  let chocochipClass: string;

  switch (mode) {
    case 'HIGHLIGHT':
      backgroundClass = 'w-full h-[4rem] px-6 rounded-[2rem] bg-custom-white';
      nameClass = 'custom-semibold-text text-custom-black';
      chocochipClass = `custom-medium-text ${
        topRank ? 'text-custom-purple' : 'text-custom-black'
      }`;
      break;
    case 'GENERAL':
    default:
      backgroundClass =
        'w-[95%] h-[2.2rem] pb-2 border-b border-custom-medium-gray bg-transparaent';
      nameClass = 'custom-medium-text text-custom-dark-gray';
      chocochipClass = 'custom-light-text text-custom-dark-gray';
  }

  let rankIcon: string;

  switch (rank) {
    case 1:
      rankIcon = '/images/icon-first-rank.png';
      break;
    case 2:
      rankIcon = '/images/icon-second-rank.png';
      break;
    case 3:
    default:
      rankIcon = '/images/icon-third-rank.png';
  }

  return (
    <div
      className={`h-[2.5rem] flex justify-between items-center ${backgroundClass}`}
    >
      <div className="flex items-center gap-2">
        {topRank ? (
          <Image
            src={rankIcon}
            width={20}
            height={20}
            alt={rank.toString()}
            className="size-[2rem]"
          />
        ) : (
          <p className="custom-medium-text text-custom-purple">{rank}</p>
        )}
        <p className={nameClass}>{name}</p>
      </div>
      <div className="flex items-center">
        <p className={chocochipClass}>{chocochip} 초코칩</p>
        {topRank ? (
          <SparklesIcon className="size-[2rem] fill-custom-light-purple" />
        ) : null}
      </div>
    </div>
  );
}
