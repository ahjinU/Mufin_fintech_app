import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  MoneyInfoElement,
} from '@/components';
import Ranks from './_components/Ranks';
import useStockStore from '../_store';
import { commaNum } from '@/utils/commaNum';

export default function Rank() {
  const { myRank } = useStockStore.getState();

  return (
    <div>
      <Header>
        <BackButton label={'날씨 주식 랭킹'} />
      </Header>
      <div className="px-[1rem] flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              link="/stock/storage"
              imageSrc={'/images/icon-all-cookies.png'}
              leftExplainText={'내 모든 초코칩'}
              leftHighlightText={`${commaNum(myRank?.balance)}초코칩`}
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
