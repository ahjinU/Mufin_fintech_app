import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  MoneyInfoElement,
} from '@/components';
import Ranks from './_components/Ranks';

export interface RankType {
  childName: string;
  balance: number;
  rank: number;
}

export default function Rank() {
  const myRank: RankType = {
    childName: '아이1',
    balance: 700,
    rank: 427,
  };
  const ranks: RankType[] = [
    {
      childName: '아이1',
      balance: 700,
      rank: 1,
    },
    {
      childName: '아이2',
      balance: 600,
      rank: 2,
    },
    {
      childName: '아이3',
      balance: 800,
      rank: 3,
    },
    {
      childName: '아이4',
      balance: 550,
      rank: 4,
    },
    {
      childName: '아이5',
      balance: 900,
      rank: 5,
    },
    {
      childName: '아이6',
      balance: 650,
      rank: 6,
    },
    {
      childName: '아이7',
      balance: 750,
      rank: 7,
    },
    {
      childName: '아이8',
      balance: 850,
      rank: 8,
    },
    {
      childName: '아이9',
      balance: 720,
      rank: 9,
    },
    {
      childName: '아이10',
      balance: 620,
      rank: 10,
    },
  ];

  return (
    <div>
      <Header>
        <BackButton label={'날씨 주식 랭킹'} link={'/stock'} />
      </Header>
      <div className="px-[1rem] flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              imageSrc={'/images/icon-all-cookies.png'}
              leftExplainText={'내 모든 초코칩'}
              leftHighlightText={'21,000,970 초코칩'}
              buttonOption={'RIGHT_ARROW'}
            />
          }
        />
        <GuideText text={'랭킹은 내 모든 초코칩(보관함+주식)으로 정해져요!'} />
        <FlexBox
          isDivided={false}
          topChildren={<Ranks ranks={ranks} myRank={myRank} />}
        />
      </div>
    </div>
  );
}
