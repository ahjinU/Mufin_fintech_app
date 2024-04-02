import { RequestListType } from '../../_types/types';
import { Button, GuideText } from '@/components';
import ChatBox from '@/components/ChatBox/ChatBox';

export default function AssesmentChat({
  childInfo,
  buttonClick,
}: {
  childInfo: RequestListType;
  buttonClick: () => void;
}) {
  const { childName, chatBotConversation } = childInfo;
  return (
    <div className="flex flex-col gap-[2rem]">
      <GuideText text="아이가 챗봇과 대화한 흔적입니다. 꼼꼼하게 확인해주세요!" />
      <div className="flex flex-col gap-[0.8rem]">
        {chatBotConversation.map((v, k) => {
          return (
            <div key={k}>
              <ChatBox
                mode={k % 2 == 0 ? 'BOT' : 'USER'}
                nickname={k % 2 == 0 ? '챗봇' : childName}
                message={v}
              />
            </div>
          );
        })}
      </div>
      <div className="sticky bottom-[1.2rem] left-[1.2rem] right-[1.2rem]">
        <Button label="심사 결정하기" mode="ACTIVE" onClick={buttonClick} />
      </div>
    </div>
  );
}
