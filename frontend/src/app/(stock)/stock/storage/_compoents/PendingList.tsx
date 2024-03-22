import { FlexBox, OtherInfoElement } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { TransactionType } from '../../_types';

export default function PeningList({ list }: { list: TransactionType[] }) {
  return (
    <div className="flex flex-col gap-[0.5rem] py-[0.5rem]">
      {list?.map(({ type, cnt, transName, amount, price }, index) => {
        return (
          <FlexBox
            mode={'LIST'}
            isDivided={false}
            key={`storage-list-${index}`}
            topChildren={
              <OtherInfoElement
                leftExplainText={`${type} ${cnt}주`}
                leftHighlightText={`${transName}(미체결)`}
                rightExplainText={`${commaNum(price)}초코칩`}
                rightHighlightText={`${commaNum(amount)}초코칩`}
                state={`${type === '매도' ? 'UP' : 'DOWN'}`}
              />
            }
          />
        );
      })}
    </div>
  );
}
