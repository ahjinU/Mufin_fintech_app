import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  MoneyInfoElement,
} from '@/components';
import Ranks from './_components/Ranks';
import { commaNum } from '@/utils/commaNum';
import { serverGetFetch } from '@/hooks/useServerFetch';

export default async function Rank() {
  const myRank = await serverGetFetch({ api: '/stock/ranking/user' });
  const myRankData = myRank.data;

  return (
    <div>
      <Header>
        <BackButton label={'날씨 주식 랭킹'} />
      </Header>
      <div className="px-[1rem] flex flex-col gap-[1rem] mb-[1.2rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              link="/stock/storage"
              imageSrc={'/images/icon-all-cookies.png'}
              leftExplainText={'내 모든 초코칩'}
              leftHighlightText={`${commaNum(myRankData?.balance)} 초코칩`}
              buttonOption={'RIGHT_ARROW'}
            />
          }
        />
        <GuideText text={'랭킹은 내 모든 초코칩(보관함+주식)으로 정해져요!'} />
        <FlexBox isDivided={false} topChildren={<Ranks />} />
      </div>
    </div>
  );
}
