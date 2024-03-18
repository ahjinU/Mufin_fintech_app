import type { Meta, StoryObj } from '@storybook/react';

import InfoShow from './InfoShow';

const meta = {
  title: 'Common/InfoShow',
  component: InfoShow,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    label: {
      description: '라벨을 등록할 수 있습니다.',
    },
    text: {
      description: '보여줄 텍스트를 입력합니다.',
    },
    copyIcon: {
      description:
        '입력된 텍스트를 클립보드에 복사하며 복사 완료 snackbar를 띄우는 아이콘을 추가합니다.',
    },
  },
} satisfies Meta<typeof InfoShow>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    label: '이메일',
    text: 'jsm0726@naver.com',
    copyIcon: true,
  },
};
